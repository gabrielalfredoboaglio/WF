/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    "./src/**/*.{html,js,jsx,md,mdx,svelte,ts,tsx,vue}",
    "./src/**/*.html",
    "./src/**/*.vue",
    "./src/**/*.jsx",
  ],
  theme: {
    extend: {
      colors: {


        "primary-color": "#ffc107",
        "secondary-color": "#ffd740",
        "bg-first": "#212529",
        "bg-second": "#303030",
        "alpha-color": "#673ab7",
        "bg-body": "#303030",
        "second-gray": "#303030",
        "body-first": "#212529",
        blk: "#212529",
        wth: "#ffffff",
        "error-color": "#ff0000",
        "border-color": "#303030",

      },
    },
  },
  plugins: [],
};
