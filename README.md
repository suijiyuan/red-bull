# red-bull

Just for fun.

## how to install it

```shell
# run as root
dnf -y update

firewall-cmd --zone=public --add-port=2222/tcp --permanent
firewall-cmd --reload

semanage port -a -t ssh_port_t -p tcp 2222

vi /etc/ssh/sshd_config
systemctl restart sshd

useradd admin
passwd admin
usermod -aG wheel admin

# run as a user who in wheel group
su - admin
sudo dnf -y install java-11-openjdk-devel
sudo dnf -y install git
sudo dnf -y install maven
sudo dnf -y install nginx
```

## how to use it