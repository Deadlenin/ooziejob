<template>
    <div class="pageContent">
        <app-header></app-header>

        <main class="appContent">
            <div class="mainContent">
                <router-view></router-view>
            </div>
        </main>

        <footer class="appFooter">
            Тут будет футер
        </footer>
    </div>
</template>

<script>
    import AppHeader from './components/AppHeader'

    export default {
        components: {
            AppHeader
        }
    }
</script>