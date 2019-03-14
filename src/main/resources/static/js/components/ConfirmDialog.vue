<template>
    <div>
        <div class="background"></div>
        <div class="confirm-component">
            <button class="main-btn" @click="okAction">Ok</button>
            <button class="main-btn" @click="closeAction">Закрыть</button>
        </div>
    </div>
</template>

<script>
    export default {
        name: "ConfirmDialog",
        methods: {
            okAction(){
                //this.$store.dispatch( 'setAddEditComponentsVisible', false );
            },
            closeAction(){
                //this.$store.dispatch( 'setAddEditComponentsVisible', false );
            },
        }
    }
</script>

<style scoped>

</style>