<template>
    <div>
        <h3>Authentication Successful</h3>
        <p>Session ID: {{ sessionId }}</p>
    </div>
</template>

<script>
export default {
    data() {
        return {
            sessionId: null,
        };
    },
    created() {
        const urlParams = new URLSearchParams(window.location.search);
        this.sessionId = urlParams.get('session');
    },
};
</script>

<style scoped>
/* Add your styles here */
</style>