<template>
    <div>
        <div class="background"></div>
        <div class="add-edit-component">
            <div class="caption">Добавить новый mapping</div>
            <div class="main-form-content">
                <div class="report">
                    <label for="report-name">Веедите имя отчета</label>
                    <input id="report-name" type="text">
                </div>
                <div class="jobs">

                </div>
            </div>
            <div class="buttons-part">
                <button class="main-btn" @click="closeDialog">Сохранить</button>
                <button class="main-btn" @click="closeDialog">Закрыть</button>
            </div>
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