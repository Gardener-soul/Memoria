import { createRouter, createWebHistory } from "vue-router";
import HomeView from "@/views/HomeView.vue";

import UserView from "@/views/UserView.vue";
import LoginView from "@/components/User/LoginView.vue";
import SignUpView from "@/components/User/SignUpView.vue";

import RollingPaperView from "@/views/RollingPaperView.vue";
import RollingPaperCreateView from "@/components/RollingPaper/RollingPaperCreateView.vue";
import RollingPaperDetailView from "@/components/RollingPaper/RollingPaperDetailView.vue";

import MessageCreateView from "@/components/message/MessageCreateView.vue";

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
    {
      path: "/rollingpaper",
      name: "rollingpaper",
      component: RollingPaperView,
    },
    {
      path: "/create",
      name: "rollingpapercreate",
      component: RollingPaperCreateView,
    },
    {
      path: "/rollingpaper/:id",
      name: "rollingpaperdetail",
      component: RollingPaperDetailView, // 새로운 상세 뷰 컴포넌트 사용
      props: true,
    },{
      path: "/message",
      name: "messagecreate",
      component: MessageCreateView,
    },
  ],
});

export default router;
