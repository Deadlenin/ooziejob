<template>
    <div class="mapping-content">
        <div title="добавить mapping" @click="addMapping" class="new-item">
            <div>добавить mapping</div>
        </div>
        <div class="reports-container">
            <report-item v-for="report in reports"
                         :key="report.id"
                         :reportName="report.reportName"
                         :reportId="report.id"
            ></report-item>
        </div>
        <div v-if="addEditComponentsVisible">
            <add-edit-component></add-edit-component>
        </div>
    </div>
</template>

<script>
    import ReportItem       from './ReportItem.vue'
    import AddEditComponent from './AddEditComponent.vue'
    import { mapGetters }   from 'vuex';

    export default {
        name: "MappingComponent",
        components: {
            ReportItem,
            AddEditComponent
        },
        computed: {
            ...mapGetters( {
                reports: 'reports',
                addEditComponentsVisible: 'addEditComponentsVisible',
            } ),
        },
        methods: {
            addMapping(){
                this.$store.dispatch( 'setAddEditComponentsVisible', {visible:true, mode:'add'});
            },

        },
        created(){
            this.$store.dispatch( 'getReports' );
        },

    }
</script>
