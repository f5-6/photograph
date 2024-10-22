<template>
  <div>
    <button @click="permit">permit Call</button>
    <button @click="auth">auth Call</button>
    <br>
    <div>
      <p>
        {{ result }}
      </p>
    </div>
  </div>
</template>

<script lang="ts" setup>
import {ref} from 'vue';
import instance from '../js/axios';

const result = ref('');

const call = (uri) => {
  instance.get(uri)
      .then(response => {
        result.value = response.data;
      })
      .catch(error => {
        result.value = error;
      });
};

const permit = () => {
  call('permit');
}

const auth = () => {
  call('auth');
};
</script>

<style scoped>
button {
  background-color: #42b983;
  margin: 5px;
  padding: 10px;
  font-size: 16px;
}
</style>
