import { createRouter, createWebHistory } from "vue-router";
import HomeView from "@/views/HomeView.vue";

import UserView from "@/views/UserView.vue";
import LoginView from "@/components/User/LoginView.vue";
import SignUpView from "@/components/User/SignUpView.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      name: "home",
      component: HomeView,
    },
    {
      path: "/user",
      name: "user",
      component: UserView,
      children: [
        {
          path: "/login",
          name: "login",
          component: LoginView,
        },
        {
          path: "/signup",
          name: "signup",
          component: SignUpView,
        },
      ],
    },
  ],
});

export default router;
