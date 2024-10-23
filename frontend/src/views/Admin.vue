<template>
  <div>
    {{ result }}
  </div>
  <button @click="logout">Logout</button>
</template>

<script lang="ts" setup>
import {onMounted, ref} from 'vue';
import instance from '../js/axios';

const result = ref('');

const test = () => {
  instance.get('permit')
      .then(response => {
        result.value = response.data;
      })
      .catch(error => {
        result.value = error;
      });
};

const logout = () => {
  instance.get('logout')
    .then(response => {
        if (response.status === 200) {
          window.location.href = '/';
        }
      })
};

onMounted(() => {
  test();
});
</script>

<style scoped>
</style>
