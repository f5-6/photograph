import '@mdi/font/css/materialdesignicons.css'
import 'vuetify/styles'
import {createVuetify} from 'vuetify'
import * as components from 'vuetify/components'
import * as directives from 'vuetify/directives'
import {VDateInput} from "vuetify/labs/components";

export default createVuetify({
    components: {
        ...components,
        VDateInput,
    },
    directives,
})

