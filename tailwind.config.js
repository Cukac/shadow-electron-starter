const defaultTheme = require('tailwindcss/defaultTheme')
module.exports = {
  future: {
    removeDeprecatedGapUtilities: true,
  },
  theme: {
    colors: {
      'primary': '#D3DEFF',
      'secondary': '#00D6CC',
      'danger': '#FF3333',
      'back1': "#0A192F",
    },
    backgroundColor: theme => theme('colors'),
    backgroundColor: {
      'primary': '#D3DEFF',
      'secondary': '#00D6CC',
      'danger': '#FF3333',
      'back1': "#0A192F",
    },
    textColor: theme => theme('colors'),
    textColor: {
      'primary': '#D3DEFF',
      'secondary': '#00D6CC',
      'danger': '#FF3333',
    }
  }
};
