<template>
    <div>
        <div class="background"></div>
        <div class="confirm-dialog">
            <div class="caption">Удалить mapping</div>
            <div>
                <div class="main-form-content">
                    {{getConfirmDialogText}}
                </div>

                <div class="buttons-part">
                    <button class="main-btn" @click="okAction">Да</button>
                    <button class="main-btn" @click="closeDialog">Отмена</button>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    import {mapGetters, mapActions} from 'vuex';

    export default {
        name: "ConfirmDialog",
        computed: {
            ...mapGetters({
                getConfirmDialogText: 'getConfirmDialogText',
                confirmOkFunction: 'getConfirmOkFunction',
                confirmCloseFunction : 'getConfirmCloseFunction'
            }),
        },
        methods: {
            okAction() {
                this.$store.dispatch(this.confirmOkFunction);
            },
            closeDialog() {
                this.$store.dispatch(this.confirmCloseFunction);
                this.$store.commit('setConfirmDialogVisible', false);
            },
        }
    }
</script>