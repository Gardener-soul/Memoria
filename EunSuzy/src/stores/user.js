// user.js
import { defineStore } from "pinia";

export const useUserStore = defineStore("user", {
  state: () => ({
    isLoggedIn: false,
    userName: null,
    userNo: null,
    img: null,
  }),
  actions: {
    setimage(data) {
      this.img = data.img;
    },
    setUser(data) {
      this.isLoggedIn = true;
      this.userName = data.userName;
      this.userNo = data.userNo; // Set userNo from the data
      this.img = data.img;
      sessionStorage.setItem("user", JSON.stringify(data));
      console.log(data.img);
    },

    clearUser() {
      this.isLoggedIn = false;
      this.userData = null;
      this.userNo = null; // Clear userNo when logging out
      this.img = null;
      sessionStorage.removeItem("user");
    },

    checkSession() {
      const user = sessionStorage.getItem("user");
      if (user) {
        const userData = JSON.parse(user);
        this.setUser(userData);
      }
    },
  },
});
