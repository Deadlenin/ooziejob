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
                    <div v-if="!addMode">
                        <job-item v-for="(item, i) in jobItems" :key="i" :index="item.id" :jobItem="item"></job-item>
                    </div>
                    <div v-else>
                        <job-item v-for="(item, i) in newJobItems" :key="i" :index="i+1" :jobItem="item"></job-item>
                    </div>
                </div>
            </div>
            <div class="buttons-part">
                <button :disabled="!AddJobEnable" class="main-btn left-button" @click="addJob">Добавить job</button>
                <button :disabled="isDisabled" class="main-btn" @click="closeDialog">Сохранить</button>
                <button class="main-btn" @click="closeDialog">Закрыть</button>
            </div>
        </div>
    </div>
</template>

<script>
    import JobItem from "./JobItem"
    import {mapGetters, mapActions ,mapMutations} from 'vuex';

    export default {
        name: "AddEditComponent",
        components: {
            JobItem
        },
        computed: {
            ...mapGetters({
                jobItems: 'jobItems',
                AddEditMode: 'AddEditMode',
                getReportName: 'getReportName',
                newJobItems: 'newJobItems'
            }),
            reportNameExist() {
                return this.reportName.length > 0
            },
            isDisabled() {
                if(this.AddEditMode === 'add'){

                    return  !(this.newJobItems.length > 0 && this.getReportName.length > 0);
                }
                else if(this.AddEditMode === 'edit'){
                    return  !(this.jobItems.length > 0 && this.getReportName.length > 0);
                }
            },
            captionText() {
                return this.AddEditMode === 'add' ? 'Добавить новый mapping' : 'Редактировать mapping';
            },
            addMode() {
                return this.AddEditMode === 'add';
            },
            AddJobEnable() {
                let jobCount = 0;
                if (this.AddEditMode === 'add') {
                    jobCount = this.newJobItems.length;
                } else {
                    jobCount = this.jobItems.length;
                }
                return jobCount < 2;
            },
            reportName:{
                get(){
                    return this.getReportName;
                },
                set(val){
                    this.setReportName(val);
                }
            }
        },
        methods: {
            ...mapMutations({
                setReportName : 'setReportName'
            }),
            ...mapActions({
                setAddEditComponentsVisible : 'setAddEditComponentsVisible'
            }),
            closeDialog() {
                this.setAddEditComponentsVisible( {visible: false, mode: null});
            },
            addJob() {
                //if (this.AddJobEnable) {
                    if (this.AddEditMode === 'add') {
                        this.$store.commit('addNewJob');
                    } else if (this.AddEditMode === 'edit') {
                        this.$store.commit('addJob');
                    }
                //}
            },
        },
        beforeDestroy() {
            this.$store.commit('cleanJobs');
        },
    }
</script>