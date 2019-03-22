<template>
    <header>
        <router-link class="menuItem" active-class="active" exact v-for="(item, i) in menuItems"
                     :class="{rSide : item.side === 'right'}"
                     :key="`menuItems${i}`"
                     :to="item.route">{{ item.title }}

        </router-link>
    </header>
</template>

<script>
    export default {
        name: "AppHeader",
        computed: {
            menuItems() {
                return [
                    {
                        title: 'Редактировать mapping',
                        route: '/'
                    },
                    {
                        title: 'Dashboards',
                        route: '/dashboards'
                    },
                    {
                        title: 'Выход',
                        side: 'right',
                        route: '/login'
                    }
                ]
             },
        },
    }
</script>
