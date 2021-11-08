const url = 'http://localhost:8080/api/';

export const adminCheck = (user) => {
    const email = '123@abv.bg';
    if (user.email===email) {
        return true;
    } else {
        return false;
    }
}

export async function create(contactName, message) {
    let postInfo = {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(contactName,
            message)
    };
    let data = await fetch((url + `.json`), postInfo);
    let response = await data.json();
    return response;
}

export async function getAll() {
    let data = await fetch(url + '.json');
    let response = await data.json();
    let all = [];
    if (response) {
        Object.keys(response).forEach(key => {
            all.push({ id: key, ...response[key] });
        });
    }
    return all;
};