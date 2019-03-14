<template>
    <header>

        <router-link class="menuItem" active-class="active" exact v-for="(item, i) in menuItems" :key="`menuItems${i}`" :to="item.route">{{ item.title }}</router-link>
    </header>
</template>

<script>
    export default {
        name: "AppHeader",
        computed:{
            menuItems(){
                return[
                    {
                        title:'Редактировать mapping',
                        route:'/'
                    },
                    {
                        title:'Dahsboard page',
                        route:'/dashboards'
                    },
                    {
                        title:'Выход',
                        route:'/login'
                    }
                ]
            }
        }
    }
</script>
