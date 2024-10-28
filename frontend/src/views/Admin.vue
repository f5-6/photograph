<template>
  <v-app>
    <v-app-bar class="border-b-md">
      <v-spacer></v-spacer>
      <v-btn @click="logout">Logout</v-btn>
    </v-app-bar>

    <v-main>
      <v-container>
        <v-form ref="formRef" v-model="valid">
          <v-row>
            <v-col cols="6">
              <v-file-input
                  v-model="form.image"
                  :rules="fileRules"
                  accept="image/*"
                  label="Upload Image"
                  prepend-icon=""
                  required
              ></v-file-input>
            </v-col>
            <v-col cols="6">
              <v-date-input
                  v-model="form.tookAt"
                  :rules="dateRules"
                  label="Select a date"
                  prepend-icon=""
                  required
              ></v-date-input>
            </v-col>
          </v-row>
          <v-row>
            <v-col cols="12">
              <v-textarea
                  v-model="form.description"
                  :rules="descriptionRules"
                  label="Description"
                  required
                  rows="2"
              ></v-textarea>
            </v-col>
          </v-row>

          <v-row>
            <v-col cols="6">
              <v-btn :disabled="!valid" color="success" @click="submitForm">
                Submit
              </v-btn>
            </v-col>
          </v-row>
        </v-form>

        <v-card class="mt-9">
          <v-data-iterator
              :items="photographs"
          >
            <template v-slot:default="{ items }">
              <v-container class="pa-2" fluid>
                <v-row dense>
                  <v-col
                      v-for="item in items"
                      :key="item.raw.id"
                      cols="auto"
                      md="4"
                  >
                    <v-card border class="pb-3" flat>
                      <v-img :src="item.raw.url"></v-img>

                      <v-btn
                          class="remove-button"
                          density="default"
                          icon="mdi-delete"
                          @click="removePhoto(item.raw.id)"
                      ></v-btn>

                      <v-list-item :subtitle="item.raw.tookAt" class="mb-2">
                        <template v-slot:title>
                          <strong class="text-h6 mb-2">{{ item.raw.description }}</strong>
                        </template>
                      </v-list-item>

                    </v-card>
                  </v-col>
                </v-row>
              </v-container>
            </template>
          </v-data-iterator>
        </v-card>

        <v-snackbar v-model="snackbar" :timeout="3000" color="success">
          {{ snackbarText }}
          <template v-slot:actions>
            <v-btn @click="snackbar = false">Close</v-btn>
          </template>
        </v-snackbar>
      </v-container>
    </v-main>
  </v-app>
</template>

<script lang="ts" setup>
import {onMounted, reactive, ref} from 'vue';
import instance from '../js/axios';
import dayjs from "dayjs";
import {VForm} from 'vuetify/components';

const logout = () => {
  instance.get('logout')
      .then(_ => {
        window.location.href = '/';
      })
};

const photographs = ref([]);

const valid = ref(false);
const formRef = ref(VForm);
const form = reactive({
  tookAt: null as Date | null,
  description: '',
  image: null as File | null,
});

const maxFileSize = 2 * 1024 * 1024;
const dateRules: Array<(v: any) => boolean | string> = [(v) => !!v || 'Date is required'];
const descriptionRules: Array<(v: any) => boolean | string> = [(v) => !!v || 'Description is required'];

const fileRules: Array<(v: FileList | null) => boolean | string> = [
  (v) => !!v || 'File is required',
  (v) => {
    if (!v || v.length === 0) return true; // 파일이 없을 경우
    return v[0].size <= maxFileSize || 'File size must be less than 2MB'; // 첫 번째 파일 크기 체크
  },
  (v) => {
    if (!v || v.length === 0) return true; // 파일이 없을 경우
    return v[0].type.startsWith('image/') || 'Only image files are allowed'; // 첫 번째 파일 형식 체크
  },
];

const snackbar = ref(false);
const snackbarText = ref('');

const submitForm = () => {
  if (formRef.value.validate()) {
    const formData = new FormData();

    formData.set('image', form.image!);
    formData.set('tookAt', dayjs(form.tookAt).format('YYYY-MM-DD'));
    formData.set('description', form.description);

    try {
      instance.post("/admin/photographs", formData, {
        headers: {
          'Content-Type': 'multipart/form-data',
        },
      });

      snackbarText.value = 'Form submitted successfully!';
      snackbar.value = true;

      setTimeout(() => {
        window.location.reload();
      }, 3000);
    } catch (e) {
      snackbarText.value = 'Error submitting form!';
      snackbar.value = true;
    }
  }
};

const getPhotos = () => {
  instance.get('/admin/photographs')
      .then(response => {
        photographs.value = response.data;
      })
}

const removePhoto = (id) => {
  instance.delete(`/admin/photographs/${id}`,)
      .then(_ => {
        const index = photographs.value.findIndex(photo => photo.id === id);

        if (index !== -1) {
          photographs.value.splice(index, 1);
        }
      })
}

onMounted(() => {
  getPhotos();
})
</script>

<style scoped>
.remove-button {
  position: absolute;
  top: 10px;
  right: 10px;
}
</style>
