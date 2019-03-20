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
                setConfirmDialogVisible: 'setConfirmDialogVisible'
            }),
            ...mapActions({
                deleteReport:'deleteReport'
            }),
            getReportById(elId) {
                if (!isNaN(parseInt(elId)))
                    this.$store.dispatch('getReportById', parseInt(elId));
            },
            delReport(id){
                this.setConfirmDialogVisible(true);
                this.deleteReport(id);
            }
        }
    }
</script>

<style scoped>

</style>