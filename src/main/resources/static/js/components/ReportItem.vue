<template>
    <div :id="id" :peropt-path="reportName" class="report-item-content">
        <div class="delReport">Удалить</div>
        <div class="reportName">{{name}}</div>
        <div  class="editReport">Редактировать</div>
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
        computed:{
            name(){
                let name = this.reportName.split('/home/reports/')[1];
                return name
            },
            id(){
                return this.reportId
            }
        }
    }
</script>

<style scoped>

</style>