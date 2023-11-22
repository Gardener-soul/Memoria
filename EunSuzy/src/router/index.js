import { createRouter, createWebHistory } from "vue-router";
import { useUserStore } from "@/stores/user.js";

import HomeView from "@/views/HomeView.vue";

import MyPageView from "@/views/MyPageView.vue";
import MyPageRollView from "@/components/MyPage/MyPageRollView.vue";
import MyPageLetterView from "@/components/MyPage/MyPageLetterView.vue";

import LoginView from "@/components/User/LoginView.vue";
import SignUpView from "@/components/User/SignUpView.vue";

import RollingPaperView from "@/views/RollingPaperView.vue";
import RollingPaperCreateView from "@/components/RollingPaper/RollingPaperCreateView.vue";
import RollingPaperDetailView from "@/components/RollingPaper/RollingPaperDetailView.vue";
import MyRollingPaperView from "@/components/RollingPaper/MyRollingPaperView.vue";

import LetterCreateView from "@/components/Letter/LetterCreateView.vue";
import LetterUpdateView from "@/components/Letter/LetterUpdateView.vue";
import MyLetterView from "@/components/Letter/MyLetterView.vue";

import EventView from "@/views/EventView.vue";
import DailyEvent from "@/components/Event/DailyEvent.vue";

import AdminView from "@/views/AdminView.vue";
import AdminUser from "@/components/admin/AdminUser.vue";
import AdminLetter from "@/components/admin/AdminLetter.vue";

const requireLogin = () => (to, from, next) => {
  const useStore = useUserStore();
  if (!useStore.isLoggedIn) {
    alert("로그인 후 이용해주세요.");
    router.push("/login");
  }
};

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      name: "home",
      component: HomeView,
    },
    {
      path: "/mypage",
      name: "mypage",
      component: MyPageView,
      children: [
        {
          path: "myroll",
          component: MyPageRollView,
        },
        {
          path: "myletter",
          component: MyPageLetterView,
        },
      ],
    },
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
    {
      path: "/rollingpaper",
      name: "rollingpaper",
      component: RollingPaperView,
    },
    {
      path: "/event",
      name: "event",
      component: EventView,
      children: [
        {
          path: ":day",
          component: DailyEvent,
        },
      ],
    },
    {
      path: "/create",
      name: "rollingpapercreate",
      component: RollingPaperCreateView,
      // beforeUpdate: requireLogin(),
    },
    {
      path: "/rollingpaper/:id",
      name: "rollingpaperdetail",
      component: RollingPaperDetailView, // 새로운 상세 뷰 컴포넌트 사용
      props: true,
    },
    {
      path: "/myroll/:id",
      name: "MyRollingPaper",
      component: MyRollingPaperView, // 새로운 상세 뷰 컴포넌트 사용
      props: true,
    },
    {
      path: "/lettercreate",
      name: "lettercreate",
      component: LetterCreateView,
    },
    {
      path: "/letterupdate/:id",
      name: "letterupdate",
      component: LetterUpdateView,
      props: true,
    },
    {
      path: "/myletter/:letterNo",
      name: "myletter",
      component: MyLetterView, // 새로운 상세 뷰 컴포넌트 사용
      props: true,
    },
    {
      path: "/admin",
      name: "admin",
      component: AdminView,
      children: [
        {
          path: "letter",
          component: AdminLetter,
        },
        {
          path: "user",
          component: AdminUser,
        },
      ],
    },
  ],
});

export default router;
