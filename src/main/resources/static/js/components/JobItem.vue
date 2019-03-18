<template>
    <div class="job-item">
        <input class="job-input" v-model="jobName" placeholder="Введите имя job'а" type="text">
        <select class="select-job" :id="jobId" @change="typeChange" name="job-type" >
            <option value="" disabled selected>Выберите тип job'а</option>
            <option value="1">Streaming</option>
            <option value="2">Build</option>
            <option value="3">Load</option>
        </select>

        <button :disabled="isDisabled" title="Добавить" class="add-btn" @click="addJob">+</button>
        <button disabled title="Удалить" class="add-btn" @click="removeJob">-</button>
    </div>
</template>

<script>
    export default {
        name: "JobItem",
        props: {
            jobItem: {
                type: Object,
                required: false
            },
        },
        data() {
            return {

                jobType: null
            }
        },
        computed: {
            jobId() {
                return `job_id_${this.jobItem.id}`;
            },
            jobName(){
                if(this.jobItem) {
                    return this.jobItem.jobName;
                }
            },
            isDisabled() {
                //return !(this.jobName.length > 0 && this.jobType !== null);
            },
        },
        methods: {
            addJob() {
                let jobItem = {
                    jobName: this.jobName,
                    jobType: this.jobType
                };
                this.$store.commit('addJob', jobItem);
            },
            removeJob() {

            },
            typeChange(event) {
                this.jobType = event.target.value;
            }
        }
    }
</script>
