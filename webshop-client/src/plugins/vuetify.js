import Vue from 'vue'
import Vuetify from 'vuetify/lib'

Vue.use(Vuetify)

export default new Vuetify({
    theme: {
        themes: {
            light: {
                primary: "#f6f5f5",
                secondary: "#145374",
                accent: "#cdc9c3",
            },
        },
    },
})