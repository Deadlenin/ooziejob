<template>
    <div :id="id" :peropt-path="reportName" class="report-item-content">
        <div :report-id="id" @click="delReport(id)" class="delReport">Удалить</div>
        <div class="reportName">{{name}}</div>
        <div :report-id="id" class="editReport" @click="getReportById(id)">Редактировать</div>
    </div>
</template>

<script>
    import {mapGetters, mapActions ,mapMutations} from 'vuex';
    export default {
        name: "ReportItem",
        props: {
            reportId: {
                type: Number,
                required: true
            },
            reportName: {
                type: String,
                required: true
            },
        },
        computed: {
            ...mapGetters({
                reports: 'reports'
            }),
            name() {
                let name = this.reportName.split('/home/reports/')[1];
                return name
            },
            id() {
                return this.reportId
            }
        },
        methods: {
            ...mapMutations({
                setConfirmDialogVisible: 'setConfirmDialogVisible',
                setConfirmDialogText: 'setConfirmDialogText',
                setConfirmOkFunction: 'setConfirmOkFunction',
                setConfirmCloseFunction : 'setConfirmCloseFunction',
                setReportToDelete : 'setReportToDelete'
            }),
            ...mapActions({
                deleteReport:'deleteReport'
            }),
            getReportById(elId) {
                if (!isNaN(parseInt(elId)))
                    this.$store.dispatch('getReportById', parseInt(elId));
            },
            delReport(id){
                let currentReport = this.reports.filter(el=>el.id===id);
                if(!!currentReport.length) {
                    this.setConfirmDialogVisible(true);
                    let repName = currentReport[0].reportName;
                    let text = `Вы действительно хотите удалить mapping для отчета
                     ${repName}`;
                    this.setReportToDelete(id);
                    this.setConfirmDialogText(text);
                    this.setConfirmOkFunction('deleteReport');
                    this.setConfirmCloseFunction('cleanReportToDelete');
                }
            }
        }
    }
</script>

<style scoped>

</style>