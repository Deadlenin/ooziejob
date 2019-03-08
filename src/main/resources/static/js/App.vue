<template>
    <div class="pageContent">
        <app-header></app-header>
        <main class="appContent">
            <router-view/>
        </main>
        <footer class="appFooter">
            Тут Будет футер
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