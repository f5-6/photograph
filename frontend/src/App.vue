<script lang="ts" setup>

</script>

<template>
  <div id="app">
    <router-link to="/" class="nav">Home</router-link>
    <router-link to="/test" class="nav">Test</router-link>
    <router-view />
  </div>
</template>

<style scoped>
.nav {
  margin: 10px;
  padding: 10px;
  font-size: 16px;
  text-decoration: none;
  color: #42b983;
}

</style>
