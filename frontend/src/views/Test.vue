<template>
    <div class="box">
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

<script lang="ts">
import instance from '../js/axios';

export default {
    name: 'Test',
    data() {
        return {
            result: ''
        }
    },
    methods: {
        permit() {
            this.call('permit');
        },
        auth() {
            this.call('auth');
        },
        call(uri: string) { 
            instance.get(uri)
            .then(response => {
                this.result = response.data;
            })
            .catch(error => {
                this.result = error;
            });
        }
    }
}
</script>

<style scoped>
button {
    background-color: #42b983;
    margin: 5px;
    padding: 10px;
    font-size: 16px;
}
</style>
