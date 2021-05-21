import Vuetify from "vuetify/lib";
import "vuetify/src/styles/main.sass";
import "vuetify/src/styles/styles.sass"
import colors from "vuetify/es5/util/colors";

export var vuetify = new Vuetify({
	icons: {
		iconfont: "md"
	},
	theme: {
		dark: false,

		themes: {
			dark: {
				primary: colors.blue.lighten2,
				secondary: colors.blue.lighten3,
			},
			light: {
				primary: colors.blue.lighten2,
				secondary: colors.blue.lighten3,
			},

		},
	}
});

