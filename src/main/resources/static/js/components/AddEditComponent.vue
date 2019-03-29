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
                <button :disabled="!AddJobEnable" class="main-btn left-button" @click="addJobInput">Добавить job
                </button>
                <button :disabled="isDisabled" class="main-btn" @click="saveChanges">Сохранить</button>
                <button class="main-btn" @click="closeDialog">Закрыть</button>
            </div>
        </div>
    </div>
</template>

<script>
    import JobItem from "./JobItem"
    import {mapGetters, mapActions, mapMutations} from 'vuex';

    export default {
        name: "AddEditComponent",
        components: {
            JobItem
        },
        computed: {
            ...mapGetters( {
                jobItems: 'jobItems',
                AddEditMode: 'AddEditMode',
                getReportName: 'getReportName',
                newJobItems: 'newJobItems',
                reportId: 'reportId'
            } ),
            isDisabled() {
                let disabled = false;
                let jobIsNotEmpty = true;
                let jobs = this.AddEditMode === 'add' ? this.newJobItems : this.jobItems;
                let reportNameIsNotEmpty = this.getReportName.trim().length > 0;
                if ( jobs ) {
                    for (let i = 0; i < jobs.length; i++) {
                        let job = jobs[ i ];
                        if ( !( job.jobName && job.jobType ) || !( job.jobName.trim().length > 0 && job.jobType !== undefined ) ) {
                            jobIsNotEmpty = false;
                            break;
                        }
                    }
                    disabled = !( reportNameIsNotEmpty && jobIsNotEmpty && jobs.length < 3 );
                    return disabled;
                }
                return true;
            },
            captionText() {
                return this.AddEditMode === 'add' ? 'Добавить новый mapping' : 'Редактировать mapping';
            },
            addMode() {
                return this.AddEditMode === 'add';
            },
            AddJobEnable() {
                let jobCount = 0;
                if ( this.AddEditMode === 'add' ) {
                    jobCount = this.newJobItems.length;
                } else {
                    jobCount = this.jobItems.length;
                }
                return jobCount < 2;
            },
            reportName: {
                get() {
                    return this.getReportName;
                },
                set( val ) {
                    this.setReportName( val );
                }
            }
        },
        methods: {
            ...mapMutations( {
                setReportName: 'setReportName',
                addNewJob: 'addNewJob',
                addJob: 'addJob',
                cleanJobs: 'cleanJobs',

            } ),
            ...mapActions( {
                setAddEditComponentsVisible: 'setAddEditComponentsVisible',
                editMapping: 'editMapping',
                saveMapping: 'saveMapping',
            } ),
            closeDialog() {
                this.setAddEditComponentsVisible( {visible: false, mode: null} );
            },
            addJobInput() {
                if ( this.AddEditMode === 'add' ) {
                    this.addNewJob();
                } else if ( this.AddEditMode === 'edit' ) {
                    this.addJob();
                }
            },
            saveChanges() {
                if ( this.AddEditMode === 'add' ) {
                    let report = {
                        id: null,
                        reportName: this.getReportName,
                        jobs: this.newJobItems,
                    };
                    this.saveMapping( report );
                } else {
                    let report = {
                        id: this.reportId,
                        reportName: this.getReportName,
                        jobs: this.jobItems,
                    };
                    this.editMapping( report );
                }
            }
        },
        beforeDestroy() {
            this.cleanJobs();
        },
    }
</script>