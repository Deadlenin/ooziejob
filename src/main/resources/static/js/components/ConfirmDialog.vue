<template>
    <div>
        <div class="background"></div>
        <div>
            <div class="background"></div>
            <div class="add-edit-component">
                <div class="caption">{{captionText}}</div>
                <div class="main-form-content">
                    <div class="report">
                        <label class="block-label" for="report-name">Отчет</label>
                        <input class="report-input" v-model="reportName" id="report-name" placeholder="Введите имя отчета"
                               type="text">
                    </div>
                    <hr/>
                    <div class="jobs">
                        <div class="job-caption">
                            Список job'ов
                        </div>
                        <div v-if="!addMode">
                            <job-item v-for="(item, i) in jobItems" :key="i" :index="item.id" :jobItem="item"></job-item>
                        </div>
                        <div v-else>
                            <job-item v-for="(item, i) in newJobItems" :key="i" :index="i+1" :jobItem="item"></job-item>
                        </div>
                    </div>
                </div>
                <div class="buttons-part">
                    <button :disabled="isDisabled" class="main-btn" @click="closeDialog">Да</button>
                    <button class="main-btn" @click="closeDialog">Отмена</button>
                </div>
            </div>
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