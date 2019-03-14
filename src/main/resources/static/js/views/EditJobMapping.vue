<template>
    <div class="mapping-part">
        <div class="page-title">Список отчетов</div>
        <mapping-component></mapping-component>
    </div>
</template>

<script>
    import MappingComponent from '../components/MappingComponent.vue'
    export default {
        name: "EditJobMapping",
        components:{
            MappingComponent
        }
    }
</script>