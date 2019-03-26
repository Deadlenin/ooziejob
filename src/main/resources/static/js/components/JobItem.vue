<template>
    <div class="job-item">
        <input class="job-input" ref="jName" v-model="jobName" placeholder="Введите имя job'а" type="text">
        <select class="select-job" :id="jobId" @change="typeChange($event)" name="job-type">
            <option v-for="option in options"
                    :value="option.value"
                    :selected="option.selected"
                    :disabled="option.disabled">{{ option.name }}
            </option>
            <!--<option value="" disabled selected>Выберите тип job'a</option>-->
            <!--<option value="1">Streaming</option>-->
            <!--<option value="2">Build</option>-->
            <!--<option value="3">Load</option>-->
        </select>
        <button :disabled="!removeEnabled" title="Удалить" class="add-btn" @click="remove(jobId)">-</button>
    </div>
</template>

<script>
    import { mapGetters, mapMutations } from 'vuex';

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
        data(){
            return {
                jobName: '',
                options: [
                    {
                        value: 0,
                        name: "Выберити тип job'a",
                        disabled: true,
                        selected: true
                    },
                    {
                        value: 1,
                        name: 'Streaming',
                        disabled: false,
                        selected: false
                    },
                    {
                        value: 2,
                        name: 'Build',
                        disabled: false,
                        selected: false
                    },
                    {
                        value: 3,
                        name: 'Load',
                        disabled: false,
                        selected: false
                    },
                ]
            }
        },
        methods: {
            remove( id ){
                this.removeJob( id.split( '_' )[1] );
            },
            typeChange( event ){
                let id = parseInt( event.target.id.split( '_' )[1] );
                let value = parseInt( event.target.value );

                if( !isNaN( id ) && !isNaN( value ) ){
                    let obj = {
                        id: id,
                        value: value
                    };
                    this.setJobType( obj );
                }
            },
            ...mapMutations( {
                setJobName: 'setJobName',
                setJobType: 'setJobType',
                removeJob: 'removeJob',
            } )
        },
        watch: {
            jobName( newVal ){
                let name = newVal || '';
                let id = parseInt( this.jobId.split( '_' )[1] );
                if( !isNaN( id ) ){
                    let obj = {
                        id: id,
                        value: name
                    };
                    this.setJobName( obj );
                }
            }
        },
        computed: {
            ...mapGetters( {
                AddEditMode: 'AddEditMode',
                jobItems: 'jobItems',
                newJobItems: 'newJobItems',
            } ),
            jobId(){
                return this.jobItem !== undefined ? `job_${this.jobItem.id}` : `job_`;
            },
            addMode(){
                return this.AddEditMode === 'add';
            },
            removeEnabled(){
                if( this.AddEditMode === 'add' ){
                    return this.newJobItems.length > 1;
                }
                else if( this.AddEditMode === 'edit' ){
                    return this.jobItems.length > 1;
                }
            }
        },
        mounted(){
            this.$refs.jName && this.$refs.jName.focus();
        },
        beforeMount(){
            this.jobName = this.jobItem.jobName;
            let typeId = this.jobItem.jobType || 0;
            for( let i = 0; i < this.options.length; i++ ){
                if( this.options[i].value === typeId ){
                    this.options[i].selected = true;
                }
                else{
                    this.options[i].selected = false;
                }
            }
        },
        beforeUpdate(){
            this.jobName = this.jobItem.jobName;
        },
        updated(){
            let typeId = this.jobItem.jobType || 0;
            for( let i = 0; i < this.options.length; i++ ){
                this.options[i].value === typeId ? this.options[i].selected = true : this.options[i].selected = false;
            }
        },

    }
</script>
