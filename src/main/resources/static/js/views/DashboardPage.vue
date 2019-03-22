<template>
    <div>Dashboard Page
        <dashboard-component></dashboard-component>
    </div>
</template>

<script>
    import DashboardComponent from '../components/DashboardComponent'
    export default {
        name: "DashboardPage",
        components:{
            DashboardComponent
        }
    }
</script>
