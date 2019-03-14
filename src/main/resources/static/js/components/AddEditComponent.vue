<template>
    <div class="background">
        <div class="add-edit-component">
            <button class="main-btn" @click="closeDialog">Закрыть</button>
        </div>
    </div>
</template>

<script>
    export default {
        name: "AddEditComponent",
        methods: {
            closeDialog(){
                this.$store.dispatch( 'setAddEditComponentsVisible', false );
            }
        }
    }
</script>

<style scoped>

</style>