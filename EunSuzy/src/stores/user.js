import { defineStore } from "pinia";

export const useUserStore = defineStore("user", {
  state: () => ({
    isLoggedIn: false,
    userData: null,
  }),
  actions: {
    setUser(data) {
      this.isLoggedIn = true;
      this.userData = data;
      sessionStorage.setItem("user", JSON.stringify(data));
    },

    clearUser() {
      this.isLoggedIn = false;
      this.userData = null;
      sessionStorage.removeItem("user");
    },

    checkSession() {
      const user = sessionStorage.getItem("user");
      if (user) {
        this.setUser(JSON.parse(user));
      }
    },
  },
});
