<template>
    <div :id="id" :peropt-path="reportName" class="report-item-content">
        <div :report-id="id" class="delReport">Удалить</div>
        <div class="reportName">{{name}}</div>
        <div :report-id="id" class="editReport" @click="getReportById(id)">Редактировать</div>
    </div>
</template>

<script>
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
            getReportById(elId) {
                if (!isNaN(parseInt(elId)))
                    this.$store.dispatch('getReportById', parseInt(elId));
            }
        }
    }
</script>

<style scoped>

</style>