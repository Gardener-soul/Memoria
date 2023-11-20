import { ref, onMounted } from "vue";
import axios from '@/util/http-common';
import { useRouter, useRoute } from "vue-router";
import moment from 'moment';

export default (() => {
const __VLS_setup = async () => {
const router = useRouter();
const route = useRoute();
const items = ref([]);

const rollingPaperId = route.params.id;

function goToThisLetter(letterNo) {
router.push({ name: "myletter", params: { letterNo: letterNo } });
}

onMounted(() => {
axios
.get(`http://localhost:8080/letter/list?rollingPaperNo=${rollingPaperId}`)
.then((response) => {
items.value = response.data;
console.log("편지 받아오기 성공", response);
})
.catch((error) => {
console.error(error);
});
});

methods: {
formatDate(dateString); {
return moment(dateString).format('YYYY-MM-DD');
}
}
const __VLS_publicComponent = (await import('vue')).defineComponent({
setup() {
return {};
},
});

const __VLS_componentsOption = {};

let __VLS_name!: 'MyRollingPaperView';
function __VLS_template() {
let __VLS_ctx!: InstanceType<import('./__VLS_types.js').PickNotAny<typeof __VLS_publicComponent, new () => {}>> & InstanceType<import('./__VLS_types.js').PickNotAny<typeof __VLS_internalComponent, new () => {}>> & {};
/* Components */
let __VLS_localComponents!: NonNullable<typeof __VLS_internalComponent extends { components: infer C; } ? C : {}> & typeof __VLS_componentsOption & typeof __VLS_ctx;
let __VLS_otherComponents!: typeof __VLS_localComponents & import('./__VLS_types.js').GlobalComponents;
let __VLS_own!: import('./__VLS_types.js').SelfComponent<typeof __VLS_name, typeof __VLS_internalComponent & typeof __VLS_publicComponent & (new () => { $slots: typeof __VLS_slots; }) >;
let __VLS_components!: typeof __VLS_otherComponents & Omit<typeof __VLS_own, keyof typeof __VLS_otherComponents>;
/* Style Scoped */
type __VLS_StyleScopedClasses = {} &
{ 'cards-container'?: boolean; } &
{ 'card'?: boolean; } &
{ 'date'?: boolean; } &
{ 'card-buttons'?: boolean; } &
{ 'card-button'?: boolean; } &
{ 'card-button'?: boolean; };
let __VLS_styleScopedClasses!: __VLS_StyleScopedClasses | keyof __VLS_StyleScopedClasses | (keyof __VLS_StyleScopedClasses)[];
/* CSS variable injection */
/* CSS variable injection end */
let __VLS_templateComponents!: {};
{
({} as JSX.IntrinsicElements).div;
({} as JSX.IntrinsicElements).div;
(__VLS_x as JSX.IntrinsicElements)['div'] = { class: ("cards-container"), };
for (const [item] of (await import('./__VLS_types.js')).getVForSourceType(__VLS_ctx.items)) {
// @ts-ignore
[items,];
{
({} as JSX.IntrinsicElements).div;
({} as JSX.IntrinsicElements).div;
(__VLS_x as JSX.IntrinsicElements)['div'] = { class: ("card"), key: ((item.letterNo)), style: (({ backgroundColor: item.backColor })), };
{
({} as JSX.IntrinsicElements).div;
({} as JSX.IntrinsicElements).div;
(__VLS_x as JSX.IntrinsicElements)['div'] = { class: ("card-body"), };
{
({} as JSX.IntrinsicElements).p;
({} as JSX.IntrinsicElements).p;
(__VLS_x as JSX.IntrinsicElements)['p'] = { class: ("text"), style: (({ color: item.fontColor, fontFamily: item.font })), };
(item.content);
}
{
({} as JSX.IntrinsicElements).img;
({} as JSX.IntrinsicElements).img;
(__VLS_x as JSX.IntrinsicElements)['img'] = { src: ((`http://localhost:8080/letter/image/${item.img}`)), };
}
{
({} as JSX.IntrinsicElements).h5;
({} as JSX.IntrinsicElements).h5;
(__VLS_x as JSX.IntrinsicElements)['h5'] = { class: ("writer"), };
(item.userName);
}
{
({} as JSX.IntrinsicElements).p;
({} as JSX.IntrinsicElements).p;
(__VLS_x as JSX.IntrinsicElements)['p'] = { class: ("date"), };
(__VLS_ctx.formatDate(item.regDate));
// @ts-ignore
[formatDate,];
}
}
{
({} as JSX.IntrinsicElements).div;
({} as JSX.IntrinsicElements).div;
(__VLS_x as JSX.IntrinsicElements)['div'] = { class: ("card-buttons"), };
{
({} as JSX.IntrinsicElements).button;
({} as JSX.IntrinsicElements).button;
(__VLS_x as JSX.IntrinsicElements)['button'] = { class: ("card-button"), };
type __VLS_0 = JSX.IntrinsicElements['button'];
const __VLS_1: import('./__VLS_types.js').EventObject<typeof undefined, 'click', {}, __VLS_0['onClick']> = {
click: $event => {
__VLS_ctx.goToThisLetter(item.letterNo);
}
};
// @ts-ignore
[goToThisLetter,];
}
}
}
}
}
if (typeof __VLS_styleScopedClasses === 'object' && !Array.isArray(__VLS_styleScopedClasses)) {
__VLS_styleScopedClasses['cards-container'];
__VLS_styleScopedClasses['card'];
__VLS_styleScopedClasses['card-body'];
__VLS_styleScopedClasses['text'];
__VLS_styleScopedClasses['writer'];
__VLS_styleScopedClasses['date'];
__VLS_styleScopedClasses['card-buttons'];
__VLS_styleScopedClasses['card-button'];
}
declare var __VLS_slots: {};
return __VLS_slots;
}
const __VLS_internalComponent = (await import('vue')).defineComponent({
setup() {
return {
items: items,
goToThisLetter: goToThisLetter,
};
},
});
return {} as typeof __VLS_publicComponent;
};
return {} as typeof __VLS_setup extends () => Promise<infer T> ? T : never;
})({} as any);
