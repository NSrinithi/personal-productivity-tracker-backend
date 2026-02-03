export async function apiFetch(url:string,option:RequestInit,navigate?:(path:string)=>void){
    try {
        const res=await fetch(url,option);
        if(res.status==401){
            localStorage.removeItem("token");
            if(navigate) navigate("/login");
            throw new Error("Session expired please login");
        }
        if(!res.ok){
            const msg=await res.text;
            throw new Error("Something went wrong");
        }
        return res;
    } catch (error:any) {
        throw new Error(error);
    }
}