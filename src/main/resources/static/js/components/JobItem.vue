<template>
    <div class="job-item">
        <input class="job-input" v-model="jobName" placeholder="Введите имя job'а" type="text">
        <select class="select-job" :id="jobId" @change="typeChange" name="job-type">
            <option value="" disabled selected>Выберите тип job'a</option>
            <option value="1">Streaming</option>
            <option value="2">Build</option>
            <option value="3">Load</option>
        </select>
        <button :disabled="!removeEnabled" title="Удалить" class="add-btn" @click="remove(jobId)">-</button>
    </div>
</template>

<script>
    import {mapGetters, mapMutations} from 'vuex';

    export default {
        name: "JobItem",
        props: {
            jobItem: {
                type: Object,
                required: false,
                default: {
                    id: 0,
                    jobName: '',
                    jobType: null
                }
            },
            index: {
                type: Number,
                required: false,
            }
        },
        methods: {
            remove(id) {
                this.removeJob(id.split('_')[1]);

            },
            typeChange(event) {
                this.jobType = event.target.value;
            },
            ...mapMutations({
                setJobName: 'setJobName',
                setJobType: 'setJobType',
                removeJob: 'removeJob',
            })
        },
        computed: {
            ...mapGetters({
                AddEditMode: 'AddEditMode',
                jobItems: 'jobItems',
                newJobItems: 'newJobItems',
            }),
            jobId() {
                return this.jobItem !== undefined ? `job_${this.jobItem.id}` : `job_`;
            },
            jobName: {
                get() {
                    if (this.jobItem.jobName) {
                        return this.jobItem.jobName;
                    }
                    else if (this.index) {
                        let target = this.newJobItems.find(el => el.id === this.index);
                        if(target.jobName){
                            return target.jobName;
                        }
                        return '';

                    }
                },
                set(value) {
                    let id = parseInt(this.jobId.split('_')[1]);
                    if (!isNaN(id)) {
                        let obj = {
                            id: id,
                            value: value
                        };
                        this.setJobName(obj);
                    }
                }
            },
            jobType: {
                get() {
                    if (this.jobItem) {
                        return this.jobItem.jobType;
                    }
                },
                set(value) {
                    let id = parseInt(this.jobId.split('_')[1]);
                    if (!isNaN(id)) {
                        let obj = {
                            id: id,
                            value: value
                        };
                        this.setJobType(obj);
                    }
                }
            },
            isDisabled() {

            },
            addMode() {
                return this.AddEditMode === 'add';
            },
            removeEnabled() {
                if (this.AddEditMode === 'add') {
                    return this.newJobItems.length > 1;
                } else if (this.AddEditMode === 'edit') {
                    return this.jobItems.length > 1;

                }
            }
        },
        mounted() {
            if (this.jobItem && this.jobItem.jobType) {
                let selElement = document.getElementById(this.jobId);
                selElement.getElementsByTagName('option')[this.jobItem.jobType].selected = true;
            }
        },

    }
</script>
