const base = {
    get() {
        return {
            url : "http://localhost:8080/springbootoc81w/",
            name: "springbootoc81w",
            // 退出到首页链接
            indexUrl: 'http://localhost:8080/springbootoc81w/front/index.html'
        };
    },
    getProjectName(){
        return {
            projectName: "乐校园二手书交易管理系统"
        } 
    }
}
export default base
