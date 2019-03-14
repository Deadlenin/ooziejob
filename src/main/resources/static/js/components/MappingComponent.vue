<template>
    <div class="mappingContent">
        <div title="добавить mapping" class="new-item"><div>добавить mapping</div></div>
        <div class="reports-container">
        <report-item v-for="report in reports"
                     :key="report.id"
                     :reportName="report.reportName"
                     :reportId="report.id"
        ></report-item>

        </div>


    </div>
</template>

<script>
    import ReportItem from './ReportItem.vue'
    import {mapGetters} from 'vuex';

    export default {
        name: "MappingComponent",
        components: {
            ReportItem
        },
        computed: {
            ...mapGetters({
                reports: 'reports',
            }),
        },
        created() {
            this.$store.dispatch('getReports');
        },

    }
</script>
