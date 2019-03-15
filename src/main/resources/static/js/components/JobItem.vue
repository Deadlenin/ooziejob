<template>
    <div class="job-item" :id="jobTypeId" >
        <input class="job-input" v-model="jobName" placeholder="Введите имя job'а" type="text">

        <!--<div class="r-button">-->
        <!--<div class="job-type">-->
        <!--<label :for="buildId" class="block-label">Build</label>-->
        <!--<input type="radio" :id="buildId" :name="jobNameId" value="build">-->
        <!--</div>-->
        <!--<div class="job-type">-->
        <!--<label :for="loadId" class="block-label">Load</label>-->
        <!--<input type="radio" :id="loadId" :name="jobNameId" value="load">-->
        <!--</div>-->
        <!--<div class="job-type">-->
        <!--<label :for="streamingId" class="block-label">Streaming</label>-->
        <!--<input type="radio" :id="streamingId" :name="jobNameId" value="streaming">-->
        <!--</div>-->
        <!--</div>-->
        <select class="select-job" @change="typeChange" name="job-type" >
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
            jobItemsId: {
                type: Number,
                required: true
            },
        },
        data() {
            return {
                jobName: "",
                jobType: null
            }
        },
        computed: {
            jobTypeId() {
                return `job-type_${this.jobItemsId}`;
            },
            isDisabled() {
                return !(this.jobName.length > 0 && this.jobType !== null);
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
