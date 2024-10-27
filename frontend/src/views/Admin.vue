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
      </v-container>
    </v-main>
  </v-app>
</template>

<script lang="ts" setup>
import {reactive, ref} from 'vue';
import instance from '../js/axios';

const logout = () => {
  instance.get('logout')
      .then(_ => {
        window.location.href = '/';
      })
};

const valid = ref(false);
const formRef = ref(null);
const form = reactive({
  tookAt: null,
  description: '',
  image: null,
});

const maxFileSize = 2 * 1024 * 1024;
const dateRules = [(v) => !!v || 'Date is required'];
const descriptionRules = [(v) => !!v || 'Description is required'];

const fileRules = [
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

const submitForm = () => {
  if (formRef.value.validate()) {
    alert('Form submitted!');
  }
};

// 수정을 위한 file load
// onMounted(() => {
// });
</script>

<style scoped>
</style>
