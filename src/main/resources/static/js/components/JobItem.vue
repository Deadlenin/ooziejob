<template>
    <div class="job-item">
        <input class="job-input" v-model="jobName" placeholder="Введите имя job'а" type="text">
        <select class="select-job" :id="jobId" @change="typeChange" name="job-type" >
            <option value="" disabled selected>Выберите тип job'a</option>
            <option  value="1">Streaming</option>
            <option  value="2">Build</option>
            <option  value="3">Load</option>
        </select>

        <button :disabled="isDisabled" title="Добавить" class="add-btn" @click="addJob">+</button>
        <button :disabled="isDisabled" title="Удалить" class="add-btn" @click="removeJob">-</button>
    </div>
</template>

<script>
    import {mapGetters} from 'vuex';
    export default {
        name: "JobItem",
        ...mapGetters({
            AddEditMode: 'AddEditMode',
        }),
        props: {
            jobItem: {
                type: Object,
                required: false
            },
        },
        data() {
            return {
                //jobType: null
            }
        },
        computed: {
            jobId() {
                return this.jobItem !== undefined ? `job_id_${this.jobItem.id}`: `job_id_0`;
            },
            jobName:{
                get(){
                    if( this.jobItem ){

                        return this.jobItem.jobName;
                    }
                },
                set(){

                }
            },
            jobType:{
                get() {
                    if(this.jobItem){
                        return this.jobItem.jobType;
                    }
                },
                set(value) {
                    //this.jobType = value;
                }
            },
            isDisabled() {
                //return !(this.jobName.length > 0 && this.jobType !== null);
            },
            addMode(){
                return this.AddEditMode === 'add';
            }
        },
        mounted(){
            if(this.jobItem){
                let selElement = document.getElementById( this.jobId );
                selElement.getElementsByTagName( 'option' )[this.jobItem.jobType + 1].selected = true;
            }

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
