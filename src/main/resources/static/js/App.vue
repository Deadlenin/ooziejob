<template>
    <div class="pageContent">
        <app-header></app-header>

        <main class="appContent">
            <div class="main-content">
                <router-view></router-view>
            </div>
        </main>

        <footer class="appFooter">
            Тут будет футер
        </footer>
        <div v-if="getConfirmDialogVisible">
            <confirm-dialog></confirm-dialog>
        </div>
    </div>
</template>

<script>
    import AppHeader from './components/AppHeader'
    import ConfirmDialog from './components/ConfirmDialog'
    import { mapGetters }   from 'vuex';
    export default {
        components: {
            AppHeader,
            ConfirmDialog
        },
        computed: {
            ...mapGetters( {
                getConfirmDialogVisible: 'getConfirmDialogVisible',
            } ),
        },
    }
</script>