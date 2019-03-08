<template>
    <header>
        <div class="menuItem" v-for="(item, i) in menuItems" :key="`menuItems${i}`" :to="item.route">{{ item.title }}</div>
    </header>
</template>

<script>
    export default {
        name: "AppHeader",
        computed:{
            menuItems(){
                return[
                    {
                        title:'Dashboard',
                        route:'/'
                    },
                    {
                        title:'Редактировать mapping',
                        route:'/EditJobMapping'
                    }

                ]

            }
        }
    }
</script>
