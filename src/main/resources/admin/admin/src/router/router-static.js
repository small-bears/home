import Vue from 'vue';
//配置路由
import VueRouter from 'vue-router'
Vue.use(VueRouter);
//1.创建组件
import Index from '@/views/index'
import Home from '@/views/home'
import Login from '@/views/login'
import NotFound from '@/views/404'
import UpdatePassword from '@/views/update-password'
import pay from '@/views/pay'
import register from '@/views/register'
import center from '@/views/center'
    import news from '@/views/modules/news/list'
    import tushufenlei from '@/views/modules/tushufenlei/list'
    import yonghu from '@/views/modules/yonghu/list'
    import qiugouhuifu from '@/views/modules/qiugouhuifu/list'
    import chat from '@/views/modules/chat/list'
    import qiugoutushu from '@/views/modules/qiugoutushu/list'
    import messages from '@/views/modules/messages/list'
    import orders from '@/views/modules/orders/list'
    import maijiayonghu from '@/views/modules/maijiayonghu/list'
    import ershoutushu from '@/views/modules/ershoutushu/list'
    import discussershoutushu from '@/views/modules/discussershoutushu/list'
    import config from '@/views/modules/config/list'


//2.配置路由   注意：名字
const routes = [{
    path: '/index',
    name: '首页',
    component: Index,
    children: [{
      // 这里不设置值，是把main作为默认页面
      path: '/',
      name: '首页',
      component: Home,
      meta: {icon:'', title:'center'}
    }, {
      path: '/updatePassword',
      name: '修改密码',
      component: UpdatePassword,
      meta: {icon:'', title:'updatePassword'}
    }, {
      path: '/pay',
      name: '支付',
      component: pay,
      meta: {icon:'', title:'pay'}
    }, {
      path: '/center',
      name: '个人信息',
      component: center,
      meta: {icon:'', title:'center'}
    }
      ,{
	path: '/news',
        name: '系统公告',
        component: news
      }
      ,{
	path: '/tushufenlei',
        name: '图书分类',
        component: tushufenlei
      }
      ,{
	path: '/yonghu',
        name: '用户',
        component: yonghu
      }
      ,{
	path: '/qiugouhuifu',
        name: '求购回复',
        component: qiugouhuifu
      }
      ,{
	path: '/chat',
        name: '联系客服',
        component: chat
      }
      ,{
	path: '/qiugoutushu',
        name: '求购图书',
        component: qiugoutushu
      }
      ,{
	path: '/messages',
        name: '留言反馈',
        component: messages
      }
      ,{
        path: '/orders/:status',
        name: '订单管理',
        component: orders
      }
      ,{
	path: '/maijiayonghu',
        name: '卖家用户',
        component: maijiayonghu
      }
      ,{
	path: '/ershoutushu',
        name: '二手图书',
        component: ershoutushu
      }
      ,{
	path: '/discussershoutushu',
        name: '二手图书评论',
        component: discussershoutushu
      }
      ,{
	path: '/config',
        name: '轮播图管理',
        component: config
      }
    ]
  },
  {
    path: '/login',
    name: 'login',
    component: Login,
    meta: {icon:'', title:'login'}
  },
  {
    path: '/register',
    name: 'register',
    component: register,
    meta: {icon:'', title:'register'}
  },
  {
    path: '/',
    name: '首页',
    redirect: '/index'
  }, /*默认跳转路由*/
  {
    path: '*',
    component: NotFound
  }
]
//3.实例化VueRouter  注意：名字
const router = new VueRouter({
  mode: 'hash',
  /*hash模式改为history*/
  routes // （缩写）相当于 routes: routes
})

export default router;
