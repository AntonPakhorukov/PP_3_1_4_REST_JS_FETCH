document.addEventListener('DOMContentLoaded', async function () {
    await showUsernameOnNavbar()
    await fillTableAboutUser();
});

async function dataAboutCurrentUser() {
    const response = await fetch("/api/user")
    return await response.json();
}
async function fillTableAboutUser(){
    const currentUserTable1 = document.getElementById("currentUserTable");
    const currentUser = await dataAboutCurrentUser();

    let currentUserTableHTML = "";
    currentUserTableHTML +=
        `<tr>
            <td>${currentUser.id}</td>
            <td>${currentUser.name}</td>
            <td>${currentUser.password}</td>
            <td>${currentUser.age}</td>
            <td>${currentUser.email}</td>
            <td>${currentUser.roles.map(role => role.role.substring(5)).join(' ')}</td>
        </tr>`
    currentUserTable1.innerHTML = currentUserTableHTML;
}

async function showUsernameOnNavbar() {
    const currentUsernameNavbar = document.getElementById("currentUsernameNavbar")
    const currentUser = await dataAboutCurrentUser();
    currentUsernameNavbar.innerHTML =
        `<strong>${currentUser.email}</strong>
                 with roles: 
                 ${currentUser.roles.map(role => role.role.substring(5)).join(' ')}`;
}