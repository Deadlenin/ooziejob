<template>

</template>

<script>
    import {mapGetters, mapActions, mapMutations} from 'vuex';

    export default {
        name: "DashboardComponent",
        computed: {},
        methods: {
            ...mapActions({
                getStatusesForDashboardPage: 'getStatusesForDashboardPage',
            })
        },
        created() {
            this.getStatusesForDashboardPage()
        }
    }
</script>
