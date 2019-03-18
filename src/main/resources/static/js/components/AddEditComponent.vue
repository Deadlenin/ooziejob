<template>
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
                    <job-item v-for="(item, i) in jobItems" :key="i" :jobItem="item"></job-item>
                </div>
            </div>
            <div class="buttons-part">
                <button :disabled="isDisabled" class="main-btn" @click="closeDialog">Сохранить</button>
                <button class="main-btn" @click="closeDialog">Закрыть</button>
            </div>
        </div>
    </div>
</template>

<script>
    import JobItem from "./JobItem"
    import {mapGetters} from 'vuex';

    export default {
        name: "AddEditComponent",
        components: {
            JobItem
        },
        data() {
            return {
                reportName: ''
            }
        },
        computed: {
            ...mapGetters({
                jobItems: 'jobItems',
                AddEditMode: 'AddEditMode',
                rName:'getReportName'
            }),
            reportNameExist() {
                return this.reportName.length > 2
            },
            isDisabled() {
                return !(this.jobItems.length > 1 && this.reportNameExist);
            },
            captionText() {
                return this.AddEditMode === 'add' ? 'Добавить новый mapping' : 'Редактировать mapping';
            }
        },
        watch:{
            rName(newVal){
                this.reportName = newVal;
            },
        },
        methods: {
            closeDialog() {
                this.$store.dispatch('setAddEditComponentsVisible', {visible: false, mode: null});
            }
        },
        beforeDestroy() {
            this.$store.commit('cleanJobs');
        },
        created() {
            //this.$store.commit('addJob', null);
        }
    }
</script>