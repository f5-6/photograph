<template>
  <div>
    {{ result }}
  </div>
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

onMounted(() => {
  test();
});
</script>

<style scoped>
</style>
