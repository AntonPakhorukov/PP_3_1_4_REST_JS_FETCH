document.addEventListener('DOMContentLoaded', async function () {
    await showUsernameOnNavbar()
    await fillTableAboutUser();
});

async function dataAboutCurrentUser() {
    const response = await fetch("/api/user")
    return await response.json();
}
async function fillTableAboutUser(){
    const currentTable = document.getElementById("currentUserTable");
    const currentUser = await dataAboutCurrentUser();

    let currentTableInHtml = "";
    currentTableInHtml +=
        `<tr>
            <td>${currentUser.id}</td>
            <td>${currentUser.name}</td>
            <td>${currentUser.password}</td>
            <td>${currentUser.age}</td>
            <td>${currentUser.email}</td>
            <td>${currentUser.roles.map(role => role.role.substring(5)).join(' ')}</td>
        </tr>`
    currentTable.innerHTML = currentTableInHtml;
}

async function showUsernameOnNavbar() {
    const currentUsernameNavbar = document.getElementById("currentUsernameNavbar")
    const currentUser = await dataAboutCurrentUser();
    currentUsernameNavbar.innerHTML =
        `<strong>${currentUser.email}</strong>
                 with roles: 
                 ${currentUser.roles.map(role => role.role.substring(5)).join(' ')}`;
}