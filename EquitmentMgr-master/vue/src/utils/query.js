import { default as api } from "./api"

const query = {
    queryDevList(param) {
        api({
            url: '/defs/list',
            method: 'get',
            param:param?param:{}
        }).then(
            (data) => {
                return data;
            }
        )
    }
}
export default query;