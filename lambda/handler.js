import sharp from 'sharp';
import AWS from 'aws-sdk';

const s3 = new AWS.S3({
  region: 'ap-northeast-2'
});

const BUCKET = '버킷명';
const DEFAULT_QUALITY = 80;
const DEFAULT_TYPE = 'contain';

export const imageResize = async (event, context, callback) => {
  const { request, response } = event.Records[0].cf;

  /** 쿼리 설명
   * w : width
   * h : height
   * f : format
   * q : quality
   * t : type (contain, cover, fill, inside, outside)
   */
  const querystring = request.querystring;
  console.log('querystring: ', querystring);
  const searchParams = new URLSearchParams(querystring);
  console.log('searchParams: ', searchParams);

  if (!searchParams.get('w') && !searchParams.get('h')) {
    return callback(null, response);
  }

  const { uri } = request;
  const [, imageName, extension] = uri.match(/\/?(.*)\.(.*)/);
  const width = parseInt(searchParams.get('w'), 10);
  const height = parseInt(searchParams.get('h'), 10);
  const quality = parseInt(searchParams.get('q'), 10) || DEFAULT_QUALITY;
  const type = searchParams.get('t') || DEFAULT_TYPE;
  const f = searchParams.get('f');
  const format = (f === 'jpg' ? 'jpeg' : f) || extension;

  try {
    const s3Object = await getS3Object(s3, BUCKET, imageName, extension);
    const resizedImage = await resizeImage(s3Object, width, height, format, type, quality);

    response.status = 200;
    response.body = resizedImage.toString('base64');
    response.bodyEncoding = 'base64';
    response.headers['content-type'] = [
      {
        key: 'Content-Type',
        value: `image/${format}`,
      },
    ];
    response.headers['cache-control'] = [
      {
        key: 'cache-control',
        value: 'max-age=31536000',
      },
    ];
    return callback(null, response);
  } catch (error) {
    return callback(error);
  }
};

async function getS3Object(s3, bucket, imageName, extension) {
  try {
    return await s3
    .getObject({
      Bucket: bucket,
      Key: decodeURI(imageName + '.' + extension),
    })
    .promise();
  } catch (error) {
    console.log('s3.getObject error: ', error);
    throw new Error(error);
  }
}

async function resizeImage(s3Object, width, height, format, type, quality) {
  try {
    return await sharp(s3Object.Body)
    .resize(width, height, { fit: type })
    .toFormat(format, {
      quality,
    })
    .toBuffer();
  } catch (error) {
    console.log('resizeImage error: ', error);
    throw new Error(error);
  }
}
